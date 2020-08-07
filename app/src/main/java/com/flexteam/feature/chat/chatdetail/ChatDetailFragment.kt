package com.flexteam.feature.chat.chatdetail

import android.animation.ValueAnimator
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flexteam.BR
import com.flexteam.R
import com.flexteam.adapter.ChatDetailAdapter
import com.flexteam.adapter.ImageSelectAdapter
import com.flexteam.base.BaseBindingModelFragment
import com.flexteam.databinding.FragmentChatDetailBinding
import com.flexteam.model.ImageSelectorModel
import com.flexteam.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.util.function.Consumer

class ChatDetailFragment :
    BaseBindingModelFragment<FragmentChatDetailBinding, ChatDetailViewModel>(),
    ImageSelectAdapter.OnImageClickListener {

    private var mIsShowMoreAction = true
    private var bottomSheetImageSelector: BottomSheetBehavior<LinearLayout>? = null
    private var bottomSheetImageSelectorLayout: LinearLayout? = null
    private var rcBottomSheet: RecyclerView? = null
    private var sendImagesButton: AppCompatButton? = null
    private var mImageList = ArrayList<ImageSelectorModel>()
    private var mImageSelectAdapter: ImageSelectAdapter? = null
    private var mPrePos = 0f
    private var mSelectedImage = ArrayList<ImageSelectorModel>()

    override fun layoutId(): Int = R.layout.fragment_chat_detail
    override fun viewModelClass(): Class<ChatDetailViewModel> = ChatDetailViewModel::class.java
    override fun bindingVariable(): Int = BR.viewModel

    override fun setupView() {
        super.setupView()
        handleAction()
        setupRecyclerView()
        getAllImagePaths(context)
        initBottomSheet()
    }


    override fun onImageClick(item: ImageSelectorModel?, position: Int) {
        mImageList[position].isChecked = !mImageList[position].isChecked
        mImageSelectAdapter?.selectImage(mImageList[position],position)
        if (mImageList[position].isChecked){
            mSelectedImage.add(mImageList[position])
        }else{
            mSelectedImage.remove(mImageList[position])
        }
        mViewModel.mActivityNavigator?.showToast(mSelectedImage.size.toString())
    }

    override fun onBackPress() : Boolean {
        super.onBackPress()
        return if (bottomSheetImageSelector?.state == BottomSheetBehavior.STATE_COLLAPSED || bottomSheetImageSelector?.state == BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetImageSelector?.state = BottomSheetBehavior.STATE_HIDDEN
            true
        }else{
            false
        }
    }

    private fun setupRecyclerView() {
        mViewModel.mChatDetailAdapter = ChatDetailAdapter(mViewModel.mListMessage)
        mLayoutBinding.rvChatDetail.adapter = mViewModel.mChatDetailAdapter
        mLayoutBinding.rvChatDetail.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        mViewModel.mNewMessageConsumer = Consumer {
            mLayoutBinding.rvChatDetail.scrollToPosition(0)
        }
    }

    private fun getAllImagePaths(context: Context?) {
        if (!mImageList.isNullOrEmpty()) mImageList.clear()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        val cursor = context?.contentResolver?.query(
            uri,
            projection,
            null,
            null,
            MediaStore.Images.Media.DATE_ADDED + " desc"
        )
        val columnIndexData = cursor?.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        mImageList.add(ImageSelectorModel().apply {
            imagePath = "access-camera"
        })
        while (cursor?.moveToNext() == true) {
            mImageList.add(ImageSelectorModel().apply {
                imagePath = cursor.getString(columnIndexData ?: 0)
            })
        }
        cursor?.close()
    }

    private fun initBottomSheet() {
        if (!isAdded || context == null) return
        mImageSelectAdapter = ImageSelectAdapter(ArrayList(), this)
        rcBottomSheet = view?.findViewById(R.id.rv_images)
        rcBottomSheet?.layoutManager = GridLayoutManager(context, 3)
        rcBottomSheet?.adapter = mImageSelectAdapter
        bottomSheetImageSelectorLayout = mLayoutBinding.layoutSelectImage.rlImageSelector
        bottomSheetImageSelector = BottomSheetBehavior.from(bottomSheetImageSelectorLayout as LinearLayout)
        bottomSheetImageSelector?.isHideable = true
        bottomSheetImageSelector?.state = BottomSheetBehavior.STATE_HIDDEN

        mLayoutBinding.btnDone.setOnClickListener {
            bottomSheetImageSelector?.state = BottomSheetBehavior.STATE_HIDDEN
            val listImagePath = ArrayList<String>()
            mSelectedImage.forEach {
                listImagePath.add(it.imagePath?:"")
            }
            mViewModel.sendImage(listImagePath)
            mImageList.forEach {
                it.isChecked = false
            }
            mSelectedImage.clear()
        }
        bottomSheetImageSelector?.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
                if (p1 < -0.8) {
                    if (p1 < mPrePos) {
                        mLayoutBinding.btnDone.visibility = View.GONE
                    }
                    mPrePos = p1
                }
            }

            override fun onStateChanged(p0: View, p1: Int) {
                if (p1 == BottomSheetBehavior.STATE_HIDDEN) {
                    mLayoutBinding.btnDone.visibility = View.GONE
                    mLayoutBinding.vShadow.visibility = View.GONE
                }
                if (p1 == BottomSheetBehavior.STATE_SETTLING) {
                    mLayoutBinding.btnDone.visibility = View.VISIBLE
                    mLayoutBinding.vShadow.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showImagesSelection() {
        mImageSelectAdapter?.setData(mImageList)
        bottomSheetImageSelector?.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun handleAction() {
        mLayoutBinding.cvMore.setOnClickListener {
            Utils.hideKeyboard(mLayoutBinding.root.context, mLayoutBinding.root)
            handleMoreAction(mIsShowMoreAction)
        }
        mLayoutBinding.etChat.setOnClickListener {
            if (!mIsShowMoreAction) {
                handleMoreAction(mIsShowMoreAction)
                mIsShowMoreAction = true
            }
        }
        mLayoutBinding.ivImage.setOnClickListener {
            showImagesSelection()
            handleMoreAction(mIsShowMoreAction)
        }
    }

    private fun handleMoreAction(isShow: Boolean) {
        mIsShowMoreAction = !mIsShowMoreAction
        val viewHeight = resources.getDimensionPixelSize(R.dimen._118sdp)
        val valueAnimator =
            ValueAnimator.ofInt(if (isShow) 0 else viewHeight, if (isShow) viewHeight else 0)
        valueAnimator.addUpdateListener {
            val value: Int = valueAnimator.animatedValue as Int
            val layoutParams = mLayoutBinding.cvMoreMenu.layoutParams
            layoutParams.height = value
            mLayoutBinding.cvMoreMenu.layoutParams = layoutParams
        }
        valueAnimator.duration = 300
        valueAnimator.start()

        val rotate = RotateAnimation(
            if (isShow) 0f else 135f, if (isShow) 135f else 0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 300
        rotate.interpolator = LinearInterpolator()
        rotate.fillAfter = true
        mLayoutBinding.ivMoreAction.startAnimation(rotate)
    }

}
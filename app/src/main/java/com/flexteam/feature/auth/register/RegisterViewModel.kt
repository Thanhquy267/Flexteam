package com.flexteam.feature.auth.register

import android.util.Patterns
import androidx.databinding.ObservableField
import com.flexteam.R
import com.flexteam.base.BaseViewModel
import com.flexteam.customview.InputFiledViewModel
import com.flexteam.utils.StringUtil
import io.reactivex.functions.Consumer

class RegisterViewModel : BaseViewModel() {
    var mFirstNameViewModel = InputFiledViewModel()
    var mLastNameViewModel = InputFiledViewModel()
    var mEmailViewModel = InputFiledViewModel()
    var mPhoneNumberViewModel = InputFiledViewModel()
    var mPasswordViewModel = InputFiledViewModel()
    var mConfirmPasswordViewModel = InputFiledViewModel()
    var mEnableButtonRegister = ObservableField<Boolean>()

    override fun onReady() {
        super.onReady()
        mFirstNameViewModel.mTitle.set(StringUtil.getString(R.string.first_name_title))
        mFirstNameViewModel.mHint.set(StringUtil.getString(R.string.first_name_hint))
        mLastNameViewModel.mTitle.set(StringUtil.getString(R.string.last_name_title))
        mLastNameViewModel.mHint.set(StringUtil.getString(R.string.last_name_hint))
        mEmailViewModel.mTitle.set(StringUtil.getString(R.string.email_title))
        mEmailViewModel.mHint.set(StringUtil.getString(R.string.email_hint))
        mPhoneNumberViewModel.mTitle.set(StringUtil.getString(R.string.phone_number_title))
        mPhoneNumberViewModel.mHint.set(StringUtil.getString(R.string.phone_number_hint))
        mPasswordViewModel.mTitle.set(StringUtil.getString(R.string.password_title))
        mPasswordViewModel.mHint.set(StringUtil.getString(R.string.password_hint))
        mConfirmPasswordViewModel.mTitle.set(StringUtil.getString(R.string.confirm_password_title))
        mConfirmPasswordViewModel.mHint.set(StringUtil.getString(R.string.confirm_password_hint))

        mFirstNameViewModel.mTextChangeConsumer = Consumer {
            mFirstNameViewModel.mError.set(if (isFullNameValid(it)) null else "First name is require")
            mEnableButtonRegister.set(checkRegisterInfo())
        }
        mLastNameViewModel.mTextChangeConsumer = Consumer {
            mLastNameViewModel.mError.set(if (isFullNameValid(it)) null else "Last name is require")
            mEnableButtonRegister.set(checkRegisterInfo())
        }
        mEmailViewModel.mTextChangeConsumer = Consumer {
            mEmailViewModel.mError.set(
                if (it.isNotEmpty()) {
                    if (isEmailValid(it)) {
                        null
                    } else {
                        "Email is invalid"
                    }
                } else {
                    "Email must not empty"
                }
            )
            mEnableButtonRegister.set(checkRegisterInfo())
        }
        mPhoneNumberViewModel.mTextChangeConsumer = Consumer {
            mPhoneNumberViewModel.mError.set(
                if (it.isNotEmpty()) {
                    if (isPhoneNumber(it)) {
                        null
                    } else {
                        "Phone number is invalid"
                    }
                } else {
                    "Phone number must not empty"
                }
            )
            mEnableButtonRegister.set(checkRegisterInfo())
        }
        mPasswordViewModel.mTextChangeConsumer = Consumer {
            checkValidPassword(
                content = it,
                ownerFiledViewModel = mPasswordViewModel,
                otherFiledViewModel = mConfirmPasswordViewModel
            )
        }
        mConfirmPasswordViewModel.mTextChangeConsumer = Consumer {
            checkValidPassword(
                content = it,
                ownerFiledViewModel = mConfirmPasswordViewModel,
                otherFiledViewModel = mPasswordViewModel
            )
        }
    }

    private fun checkValidPassword(
        content: String,
        ownerFiledViewModel: InputFiledViewModel,
        otherFiledViewModel: InputFiledViewModel
    ) {
        if (content.isNotEmpty()) {
            if (isPasswordValid(content)) {
                if (content == otherFiledViewModel.mContent.get().toString()) {
                    mPasswordViewModel.mError.set(null)
                    mConfirmPasswordViewModel.mError.set(null)
                } else {
                    ownerFiledViewModel.mError.set("${ownerFiledViewModel.mTitle.get()} not match")
                }
            } else {
                ownerFiledViewModel.mError.set("${ownerFiledViewModel.mTitle.get()} must than 6 characters")
            }
        } else {
            ownerFiledViewModel.mError.set("${ownerFiledViewModel.mTitle.get()} must not empty")
        }
        mEnableButtonRegister.set(checkRegisterInfo())
    }

    private fun checkRegisterInfo(): Boolean {
        return (mFirstNameViewModel.mError.get() == null
                && mLastNameViewModel.mError.get() == null
                && mEmailViewModel.mError.get() == null
                && mPhoneNumberViewModel.mError.get() == null
                && mPasswordViewModel.mError.get() == null
                && mConfirmPasswordViewModel.mError.get() == null)
    }

    private fun isFullNameValid(name: String): Boolean {
        return name.isNotEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPhoneNumber(phoneNumber: String): Boolean {
        return (Patterns.PHONE.matcher(phoneNumber).matches() && phoneNumber.length > 9)
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }
}
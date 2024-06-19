package com.haikal.athena.costumview

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.haikal.athena.R

class InputPasswordView : AppCompatEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val minimumCharInput: Int = 8
    private var passwordToggleEnabled = false

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val totalCharCount = start + count
                error = if (totalCharCount < minimumCharInput)
                    context.getString(R.string.ed_login_password_error)
                else
                    null

                passwordToggleEnabled = totalCharCount >= minimumCharInput
                updatePasswordToggle()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        hint = context.getString(R.string.ed_login_password_hint)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updatePasswordToggle() {
        if (passwordToggleEnabled) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(
                null, null,
                context.getDrawable(R.drawable.ic_baseline_visibility_24), null
            )
        } else {
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
        }
    }
}
package com.my.android_jetpak.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.my.android_jetpak.R
import io.github.kbiakov.codeview.adapters.CodeWithDiffsAdapter
import io.github.kbiakov.codeview.adapters.Options
import io.github.kbiakov.codeview.highlight.ColorTheme
import io.github.kbiakov.codeview.highlight.Font
import kotlinx.android.synthetic.main.fragment_javacode.view.*
import java.util.*

class MVVMExtrasFragment : Fragment() {
    private lateinit var mContext: Activity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.fragment_javacode, container, false)
        mContext = this.activity!!
        initViews(mView)
        return mView
    }


    fun initViews(mView: View) {

        val codeView = mView.code_view
        codeView.setCode(getString(R.string.mvvm_extras), "java")
        val diffsAdapter = CodeWithDiffsAdapter(mContext)


        Objects.requireNonNull(codeView.getOptions())
            ?.withLanguage("java")
            ?.code = getString(R.string.mvvm_extras)
        codeView.updateAdapter(diffsAdapter)

        codeView.getOptions()!!
            .shortcut(10, "Show all")


        codeView.updateOptions(object : Function1<Options, Unit> {
            @Override
            override fun invoke(options: Options) {
                options.withFont(Font.Consolas)
                    .withTheme(ColorTheme.SOLARIZED_LIGHT)
                    .withShadows()
                    .shortcut = false
            }
        })

    }
}

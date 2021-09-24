package com.example.mall_library.global.ui.loader

import android.content.Context
import androidx.appcompat.app.AppCompatDialog
import com.example.mall_library.R
import com.wang.avi.AVLoadingIndicatorView

//class MallLoader     //这里都是为了使用第三方库 加载的动态图片 AVLoadingIndicatorView,
//通过dialog
 object MallLoader    //这里采用静态类
{
    private fun createDialog(
        context: Context?,
        loadingView: AVLoadingIndicatorView
    ):AppCompatDialog
    {
        val dialog=AppCompatDialog(context,R.style.dialog)
              //此处在app模块的res-value- new-values resource file-style.xml，后来本模块不能使用
              //发现本模块连可写的res 资源文件夹都木有，建立资源文件夹value就好（res 自动产生）
              //右击模块mall-library, new-Android resource directory-values, 然后把上面style.xml
              //剪切到这里（会有refactor提示...）
        //获取屏幕的宽高--去网上找轮子，比如GITHUB,查询utils 什么的，推荐 AndroidUtilCode 库


     return dialog

    }
}
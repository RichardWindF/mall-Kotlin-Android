package com.example.mall_library.global.ui.loader

import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import com.blankj.utilcode.util.ScreenUtils
import com.example.mall_library.R
import com.wang.avi.AVLoadingIndicatorView
import com.wang.avi.Indicator
import com.wang.avi.indicators.BallClipRotateIndicator

//class MallLoader     //这里都是为了使用第三方库 加载的动态图片 AVLoadingIndicatorView,
//通过dialog
 object MallLoader    //这里采用静态类
{
    private val LOADER_SIZE_SCALE=8         //试出来的缩放比-
    private val LOADER_OFFSET_SCALE=10      //试出的偏移量-

    //private val LOADERS=ArrayList<AppCompatDialog>()
    private val LOADERS=ArrayList<AppCompatDialog>()   //写法于Java的区别。存储Dialogs
    //默认的loader
    private val DEFAULT_LOADER=BallClipRotateIndicator()


    private fun createDialog(
        context: Context?,
        avLoadingIndicatorView: AVLoadingIndicatorView
    ):AppCompatDialog
    {
        val dialog=AppCompatDialog(context,R.style.dialog)
              //此处在app模块的res-value- new-values resource file-style.xml，后来本模块不能使用
              //发现本模块连可写的res 资源文件夹都木有，建立资源文件夹value就好（res 自动产生）
              //右击模块mall-library, new-Android resource directory-values, 然后把上面style.xml
              //剪切到这里（会有refactor提示...）
        //获取屏幕的宽高--去网上找轮子，比如GITHUB,查询utils 什么的，推荐 AndroidUtilCode 库


        //一般要使用什么工具，应该先网上找，--工作量的原因
        val deviceWidth=ScreenUtils.getScreenWidth()      //推荐 AndroidUtilCode 库中的
        val deviceHeight=ScreenUtils.getScreenHeight()
        val dialogWindow=dialog.window    //java的 getWindow()
        dialog.setContentView(avLoadingIndicatorView)

        if (dialogWindow!=null) {
            val lp:WindowManager.LayoutParams =dialogWindow.attributes
            lp.width=deviceWidth/ LOADER_SIZE_SCALE
            lp.height=deviceHeight/ LOADER_SIZE_SCALE
            lp.height=lp.height+deviceHeight/ LOADER_OFFSET_SCALE     //缩小，偏上位置
            lp.gravity=Gravity.CENTER

        }

        LOADERS.add(dialog)     //生成多个对话框时候保存
     return dialog    //这里就有了一个对话框
    }

    //显示对话框
    fun showLoading(context: Context?,type:Enum<LoaderStyles>)
    {
        showLoading(context,type.name)
    }

    fun showLoading(context: Context?,type:String)
    {
        val avLoadingIndicatorView=AVLoadingIndicatorView(context)
        avLoadingIndicatorView.setIndicator(type)
        createDialog(context,avLoadingIndicatorView).show()
    }

    @JvmOverloads   //意思是重载，不加也可以
    fun showLoading(context: Context?,indicator:Indicator= DEFAULT_LOADER) //缺省值
    {
        val avLoadingIndicatorView=AVLoadingIndicatorView(context)
        avLoadingIndicatorView.indicator=indicator
        createDialog(context,avLoadingIndicatorView).show()
    }

    // 把正在转的loading都停止掉
    fun stopLoading()
    {
        for(dialog in LOADERS)    //daialog:AppCompatDialog
        {
            if(dialog.isShowing){ dialog.cancel()}
        }

        LOADERS.clear()
    }


}
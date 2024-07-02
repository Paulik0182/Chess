package com.nayya.chess.utils.image

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Реализацией интерфейса [ImageLoader], для загрузки изображений в ImageView с использованием библиотеки Glide.
 *
 */
class GlideImageLoader : ImageLoader<ImageView> {

    /** Инициализируется значением по умолчанию из констант ImageConst*/
//    private val imageView = UsedConst.ImageConst.getDefaultImageResourceId()

    /** Загружает изображение по указанному URL в указанный контейнер ImageView с помощью библиотеки Glide
     *
     * @property url URL Адрес изображения.
     * @property container Контейнер в которое помещается изображение.
     * @see Glide.with Запускает цепочку вызовов Glide, используя контекст приложения.
     * @see asBitmap Указывает, что изображение должно быть загружено как Bitmap.
     * @see placeholder(...) Устанавливает изображение-заглушку из imageView,
     *           пока основное изображение не загружено.
     * @see load(...) Загружает изображение по указанному URL.
     * @see circleCrop Добавляет обрезку изображения в форму круга.
     * @see into(...) Отображает загруженное изображение в указанном контейнере ImageView.
     * */
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context.applicationContext)
            .asBitmap()
            .placeholder(null)
            .load(url)
//            .circleCrop()
            .into(container)
    }

    override fun loadInto(resource: Drawable, container: ImageView) {
        Glide.with(container.context.applicationContext)
            .asBitmap()
            .placeholder(null)
            .load(resource)
            .into(container)
    }

    override fun loadInto(resource: Int, container: ImageView) {
        Glide.with(container.context.applicationContext)
            .asBitmap()
            .placeholder(null)
            .load(resource)
            .into(container)
    }
}

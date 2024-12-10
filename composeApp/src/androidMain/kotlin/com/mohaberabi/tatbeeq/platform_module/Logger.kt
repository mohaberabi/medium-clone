import android.util.Log
import com.mohaberabi.tatbeeq.core.util.error.AppError


actual object AppLogger {

    actual fun e(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            Log.e(tag, message, throwable)
        } else {
            Log.e(tag, message)
        }
    }

    actual fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    actual fun i(tag: String, message: String) {
        Log.i(tag, message)
    }

    actual fun appError(error: AppError) {
        Log.e("appError", error.toString())
    }

}
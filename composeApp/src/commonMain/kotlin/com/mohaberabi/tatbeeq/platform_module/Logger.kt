import com.mohaberabi.tatbeeq.core.util.error.AppError


expect object AppLogger {
    fun e(tag: String, message: String, throwable: Throwable? = null)
    fun d(tag: String, message: String)
    fun i(tag: String, message: String)
    fun appError(error: AppError)
}
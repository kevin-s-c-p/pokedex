import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    companion object {
        const val PROGRESS_START = 0
        const val PROGRESS_END = 200
    }

    private val _progress = MutableLiveData<Int>()
    private val _message = MutableLiveData<String>()
    private val _response = MutableLiveData<Any>()

    val progress: LiveData<Int>
        get() = _progress

    val message: LiveData<String>
        get() = _message

    protected fun updateProgress(progress: Int) {
        _progress.value = progress
    }

    protected fun updateMessage(message: String) {
        _message.value = message
        updateProgress(PROGRESS_END)
    }

    val response: LiveData<Any>
        get() = _response

    protected fun updateValueResponse(response: Any) {
        _response.value = response
        updateProgress(PROGRESS_END)
    }
}
package com.juggist.baselibrary

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
    工厂方法模式
 */
class MessageModel : ViewModel() {
    val nameLiveData: MutableLiveData<MessageA.A> = MutableLiveData(MessageA.A("tom"))
    val talkLiveData: MutableLiveData<MessageB.B> = MutableLiveData(MessageB.B("hello"))
    fun getSocket(type: String, response: String) {
        when (type) {
            "1" -> {
                nameLiveData.postValue(MessageAFactory().productMessage().parse("juggist"))
            }
            "2" -> {
                talkLiveData.postValue(MessageBFactory().productMessage().parse("hi"))
            }
        }

    }
    fun testSequence(){
        val sequence : Sequence<Int> = sequenceOf(1,2,3,4,5)
            .map {
                Log.i("sequence","map $it")
                it * 2
            }
            .filter {
                Log.i("sequence","filter $it")
                it % 3 == 0
            }
        sequence.forEach {
            Log.i("sequence","forEach $it")
        }
    }

}

interface Message<T>{
  fun parse(response:String) : T
}
class MessageA : Message<MessageA.A>{
    override fun parse(response:String) : A {
        return A(response)
    }
    data class A(val name:String)
}
class MessageB : Message<MessageB.B>{
    override fun parse(response:String) : B {
        return B(response)
    }
    data class B(val talk:String)

}
interface IFactory<T>{
    fun productMessage():Message<T>
}
class MessageAFactory :IFactory<MessageA.A>{
    override fun productMessage(): Message<MessageA.A> {
        return MessageA()
    }

}
class MessageBFactory :IFactory<MessageB.B>{
    override fun productMessage(): Message<MessageB.B> {
        return MessageB()
    }
}





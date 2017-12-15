package jetbrains.buildServer.sesPlugin.sqs

import jetbrains.buildServer.sesPlugin.teamcity.SQSBean

interface SQSMessagesReceiver<T> {
    fun receiveMessages(bean: SQSBean): ReceiveMessagesResult<T>
}
package jetbrains.buildServer.sesPlugin.sqs

import jetbrains.buildServer.sesPlugin.sqs.result.CheckConnectionResult
import jetbrains.buildServer.sesPlugin.teamcity.SQSBean

interface SQSConnectionChecker {
    fun checkConnection(bean: SQSBean): CheckConnectionResult
}
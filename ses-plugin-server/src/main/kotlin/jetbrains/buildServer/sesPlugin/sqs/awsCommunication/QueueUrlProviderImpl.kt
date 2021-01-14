/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jetbrains.buildServer.sesPlugin.sqs.awsCommunication

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.GetQueueUrlRequest
import jetbrains.buildServer.sesPlugin.teamcity.SQSBean

class QueueUrlProviderImpl : QueueUrlProvider {
    override fun getQueueUrl(amazonSQS: AmazonSQS, bean: SQSBean): String {
        val queueName = bean.queueName
        val accountId = bean.accountId

        val queueUrlResult = try {
            amazonSQS.getQueueUrl(GetQueueUrlRequest().withQueueName(queueName).withQueueOwnerAWSAccountId(accountId))
        } catch (ex: Exception) {
            throw AmazonSQSCommunicationException("Cannot get queue url with name $queueName and owner $accountId", ex)
        }

        return queueUrlResult.queueUrl
    }
}
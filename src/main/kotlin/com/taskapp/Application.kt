package com.taskapp

import com.taskapp.features.add.addTaskToWorkSpace.configureAddTaskToWorkSpaceRouting
import com.taskapp.features.add.addUserToTask.configureAddUserToTask
import com.taskapp.features.add.addUserToWorkSpace.configureAddUserToWorkSpaceRouting
import com.taskapp.features.add.addWorkspace.configureAddWorkSpaceRouting
import com.taskapp.features.get.getTasksFromWorkSpace.configureGetTasksFromWorkSpaceRouting
import com.taskapp.features.get.getUsers.configureGetUsersRouting
import com.taskapp.features.get.getWorkSpaces.configureGetWorkSpaceRouting
import com.taskapp.features.auth.login.configureLoginRouting
import com.taskapp.features.auth.register.configureRegisterRouting
import com.taskapp.features.delete.deleteWorkSpace.configureDeleteWorkSpace
import com.taskapp.features.files.configureGetAvatar
import com.taskapp.features.files.configureGetVoiceMessage
import com.taskapp.features.files.configureUploadNewAvatar
import com.taskapp.features.files.configureUploadVoiceMessage
import com.taskapp.features.realTime.configureWebSockets
import com.taskapp.features.realTime.taskNotes.configureGetNotesFromTask
import com.taskapp.features.realTime.taskNotes.configureNotes
import com.taskapp.features.set.setTaskValues.configureSetTaskValuesRouting
import com.taskapp.features.set.setUserValues.configureSetUserStatusRouting
import com.taskapp.features.realTime.workSpaceChat.configureGetMessagesFromWorkSpace
import com.taskapp.features.realTime.workSpaceChat.configureWorkSpaceChat
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import com.taskapp.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {
    embeddedServer(CIO, port = 8080, host = "localhost") {
        //подключение базы данных
        Database.connect(
            user = "postgres",
            url = "jdbc:postgresql://localhost:5432/taskappdb",
            password = "danil2002gimbarr1980kryt",
            driver = "org.postgresql.Driver"
        )
        //Конфигурирую тут все
        configureSerialization()
        // Регистрация и аунтификация
        configureRegisterRouting()
        configureLoginRouting()

        //workSpaces
        configureAddWorkSpaceRouting()
        configureAddTaskToWorkSpaceRouting()
        configureGetWorkSpaceRouting()
        configureAddUserToTask()
        configureAddUserToWorkSpaceRouting()
        configureDeleteWorkSpace()

        // tasks
        configureGetTasksFromWorkSpaceRouting()
        configureSetTaskValuesRouting()

        //users
        configureGetUsersRouting()
        configureSetUserStatusRouting()

        //webSockets

        configureWebSockets()


        //messages
        configureWorkSpaceChat()
        configureGetMessagesFromWorkSpace()

        configureUploadNewAvatar()

        configureGetAvatar()

        configureGetVoiceMessage()

        configureUploadVoiceMessage()

        //notes

        configureNotes()

        configureGetNotesFromTask()

    }.start(wait = true)
}

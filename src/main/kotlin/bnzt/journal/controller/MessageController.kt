package bnzt.journal.controller

import bnzt.journal.exception.NotFoundException
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("message")
class MessageController {
    private var counter = 4
    private val messages = ArrayList<HashMap<String, String>>()

    init {
        messages.add(hashMapOf("id" to "1", "text" to "first message"))
        messages.add(hashMapOf("id" to "2", "text" to "second message"))
        messages.add(hashMapOf("id" to "3", "text" to "third message"))
    }

    @GetMapping
    fun list(): List<MutableMap<String, String>> {
        return messages
    }

    @GetMapping("{id}")
    fun getOne(@PathVariable id: String): Map<String, String> {
        return getMessage(id)
    }

    private fun getMessage(@PathVariable id: String): MutableMap<String, String> {
        return messages.stream()
            .filter { message: Map<String, String> ->
                message["id"] == id
            }
            .findFirst()
            .orElseThrow { NotFoundException() }
    }

    @PostMapping
    fun create(@RequestBody message: HashMap<String, String>): Map<String, String> {
        message["id"] = counter++.toString()
        messages.add(message)
        return message
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: String, @RequestBody message: HashMap<String, String>): Map<String, String> {
        val messageFromDb = getMessage(id)
        messageFromDb.putAll(message)
        messageFromDb["id"] = id
        return messageFromDb
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String) {
        val message = getMessage(id)
        messages.remove(message)
    }
}
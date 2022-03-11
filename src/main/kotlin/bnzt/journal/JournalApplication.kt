package bnzt.journal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JournalApplication

fun main(args: Array<String>) {
	runApplication<JournalApplication>(*args)
}

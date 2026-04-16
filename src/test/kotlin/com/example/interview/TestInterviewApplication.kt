package com.example.interview

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<InterviewApplication>().with(TestcontainersConfiguration::class).run(*args)
}

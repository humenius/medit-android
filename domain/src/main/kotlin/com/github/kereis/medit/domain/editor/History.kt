package com.github.kereis.medit.domain.editor

import java.util.Stack

/**
 * # History
 * Stores different states of data type [T]. Each operation is thread-safe.
 *
 * @author kereis
 */
class History<T> {
    private val historyList: Stack<T> = Stack()

    val iterator: Iterator<T> get() = historyList.iterator()

    @Synchronized
    fun add(element: T): Boolean {
        historyList.push(element)

        return true
    }

    @Synchronized
    fun remove(element: T): Boolean {
        historyList.remove(element)

        return true
    }

    @Synchronized
    fun jumpToPreviousState(): T = historyList.pop()
}

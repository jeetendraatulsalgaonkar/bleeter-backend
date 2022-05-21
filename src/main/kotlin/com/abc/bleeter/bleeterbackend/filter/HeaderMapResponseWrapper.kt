package com.abc.bleeter.bleeterbackend.filter

import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper

class HeaderMapResponseWrapper(private val response: HttpServletResponse) : HttpServletResponseWrapper(response) {

    private val headerMap = mutableMapOf<String, String>();

    /**
     * add a header with given name and value
     *
     * @param name
     * @param value
     */
    override fun addHeader(name: String, value: String) {
        this.headerMap.put(name, value)
    }

    override fun getHeader(name: String): String? {
        var headerValue = super.getHeader(name)
        if (this.headerMap.containsKey(name)) {
            headerValue = this.headerMap[name]
        }
        return headerValue
    }

    /**
     * get the Header names
     */
    override fun getHeaderNames(): MutableCollection<String> {
        val names: Collection<String> = super.getHeaderNames()
        for (name in this.headerMap.keys) {
            println("Name: $name")
            names.toMutableList().add(name)
        }
        return names.toMutableList()
    }

    override fun getHeaders(name: String): MutableCollection<String> {
        val values: MutableList<String> = super.getHeaders(name).toMutableList()
        if (this.headerMap.containsKey(name)) {
            this.headerMap[name]?.let { values.add(it) }
        }
        return values.toMutableList()
    }
}
package com.abc.bleeter.bleeterbackend.filter

import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BleeterFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("Inside Filter.")
        val wrapper = HeaderMapResponseWrapper(response)
        wrapper.addHeader("Access-Control-Allow-Origin", "*")
        println(wrapper.headerNames)
        filterChain.doFilter(request, response)
    }

}
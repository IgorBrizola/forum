package br.com.project.forum.mapper

interface Mapper<T, U> {

    fun map(t: T): U

}

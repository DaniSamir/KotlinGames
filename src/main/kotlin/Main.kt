package org.example

import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar: ")

    val busca = leitura.nextLine()

    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()

    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()

   // println(json)

//    --com construtor
//    val meuJogo = Jogo( "Batman: Arkham Asylum Game of the Year Edition", "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1702934705")
//    println(meuJogo)
//
//    --sem construtor
//    meuJogo.titulo = "Batman: Arkham Asylum Game of the Year Edition"
//    meuJogo.capa = "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1702934705"
//    val meuNovoJogo = Jogo( capa= "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1702934705", titulo="Batman: Arkham Asylum Game of the Year Edition", )
//    println(meuNovoJogo)

    val gson = Gson()
    val meuInfoJogo =
        gson.fromJson(json, InfoJogo::class.java) // Jogo::class.java - Avisa que isso será transformado em uma classe
    // Utilização do Try Catch no Kotlin
    //    try {
    //        val meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
    //    } catch (ex: NullPointerException){
    //        println("Jogo inexistente. Tente outro id.")
    //    }

    //Utilização do runCatching no Kotlin - Mais indicado
    var meuJogo: Jogo? = null

    val resultado = runCatching {
        meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
        println(meuJogo)
    }
    resultado.onFailure {
        println("Jogo inexistente. Tente outro id.")
    }


    resultado.onSuccess {
        println("Deseja inserir uma descricao personalizada? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {
            println("Insira a descricao personalizada: ")

            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
            println(meuJogo)
        } else {
            meuJogo?.descricao = meuJogo?.titulo
            println(meuJogo)
        }
    }

}
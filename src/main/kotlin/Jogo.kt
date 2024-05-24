package org.example

data class Jogo( val titulo:String, val capa:String) {
//    var titulo = ""
//    var capa = "" //var permitem a modificação de valores
//    var descricao = "" passando de forma vazia
    var descricao:String? = null // val não permitem a modificação de valores (final) Nulo precisa dizer o tipo
    override fun toString(): String {
        return "Meu Jogo:'\n"+
                "Título: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao"
    }
}

//class Jogo(@SerializedName("title") val titulo:String,@SerializedName("thumb") val capa:String) {}
//Ctrl + Alt + O - tirar importes não utilizados
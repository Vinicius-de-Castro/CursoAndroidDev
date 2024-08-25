class Livro(
    val nome: String, val genero: String,
    val autor: String, private val pagsTotais: Int,
    private var pagsLidas: Int
){
    private fun lerPags(){
        println("Quantas páginas a mais foram lidas desde a ultima sessão?")
        val n: Int = readln().toIntOrNull() ?: 0
        if (pagsLidas + n > pagsTotais || n <= 0) {
            println("Quantidade de páginas inválida")
        }
        else {
            this.pagsLidas+= n
            println("Progresso ")
        }
    }
    private fun info(){
        println("Nome: ${this.nome}")
        println("Genero: ${this.genero}")
        println("Autor: ${this.autor}")
        println("Páginas: ${this.pagsTotais}")
        println("Páginas lidas: ${this.pagsLidas}")
        println("Porcentagem de conclusão: ${100*this.pagsLidas/this.pagsTotais}%")
    }
    fun selecionar(){
        println("Você selecionou o livro \"${this.nome}\"")
        var options = 0
        while (options != -1){
            println("Opções:")
            println("Informações do livro(1) // Atualizar progresso de leitura(2) // Voltar(-1)")
            options = readln().toIntOrNull() ?: 0
            when (options){
                -1 -> {
                    println("Pesquisa encerrada")
                }
                1 -> {
                    this.info()
                }
                2 -> {
                    this.lerPags()
                }
                else -> {
                    println("Valor inválido")
                }
            }
        }
    }
}
class Biblioteca {
    private var livros = mutableListOf<Livro>()
    fun cadastro(){
        println("Cadastro de livro")
        println("Digite o nome do livro")
        val nome: String = readln().ifBlank {"Desconhecido"}
        println("Digite o gênero do livro")
        val genero: String = readln().ifBlank {"Desconhecido"}
        println("Digite o nome do autor do livro")
        val autor: String = readln().ifBlank {"Desconhecido"}
        println("Digite o número de páginas do livro")
        var pagsTotais: Int = readln().toIntOrNull() ?:0
        while (pagsTotais <= 0){
            println("Quantidade de páginas inválida, digite novamente")
            pagsTotais = readln().toIntOrNull() ?:0
        }
        println("Digite o número de páginas lidas")
        var pagsLidas: Int = readln().toIntOrNull() ?:0
        while (pagsLidas < 0 || pagsLidas > pagsTotais){
            println("Quantidade de páginas inválida, digite novamente")
            pagsLidas = readln().toIntOrNull() ?: -1
        }
        livros.add(Livro(nome, genero, autor, pagsTotais, pagsLidas))
    }
    fun seletorDeBusca(){
        var input = 0
        println("Que tipo de busca você deseja realizar?")
        while (input != -1){
            println("Opções de busca:")
            println("Por nome(1) // Por gênero(2) || Por autor(3) || Cancelar(-1)")
            input = readln().toIntOrNull() ?: 0
            when (input){
                -1 -> {
                    println("Busca cancelada")
                }
                in 1 .. 3 -> {
                    this.busca(input)
                    break
                }
                else -> {
                    println("Comando desconhecido")
                }
            }
        }
    }
    private fun busca(seletor : Int){
        var filtro = ""
        var resultados = mutableListOf<Livro>()
        while (filtro == "" || resultados.isEmpty()) {
            when(seletor){
                1 -> {
                    println("Digite o nome do livro que quer buscar:")
                    filtro = readln()
                    resultados = this.livros.filter { it.nome == filtro }.toMutableList()
                }
                2 -> {
                    println("Digite o gênero de livro que quer buscar:")
                    filtro = readln()
                    resultados = this.livros.filter { it.genero == filtro }.toMutableList()
                }
                3 -> {
                    println("Digite o autor que quer buscar:")
                    filtro = readln()
                    resultados = this.livros.filter { it.autor == filtro }.toMutableList()
                }
            }
            if (resultados.isEmpty()) println("Nenhum livro encontrado")
        }
        println("Livros encontrados:")
        for (i in resultados.indices){
            println("${i+1}. ${resultados[i].nome}, Autor: ${resultados[i].autor}, Gênero: ${resultados[i].genero}")
        }
        var index = 0
        while (index != -1){
            println("Opções:")
            println("Selecionar livro(Indíce) // Cancelar(-1)")
            index = readln().toIntOrNull() ?: 0
            when (index){
                -1 -> {
                    println("Pesquisa cancelada")
                }
                in 1..(resultados.size) -> {
                    this.livros[index-1].selecionar()
                    break
                }
                else -> {
                    println("Valor inválido")
                }
            }
        }
    }
}

fun main(){
    var input = 0
    val bib = Biblioteca()
    println("Seja bem vindo!")
    println("O que fazer hoje?")
    while (input != -1){
        println("Opções:")
        println("Cadastrar livro(1) // Buscar livro(2) // Encerrar sessão(-1)")
        input = readln().toIntOrNull() ?: 0
        when (input){
            -1 -> println("Até a próxima!")
            1 -> bib.cadastro()
            2 -> bib.seletorDeBusca()
            else -> println("Comando desconhecido")
        }
    }
}
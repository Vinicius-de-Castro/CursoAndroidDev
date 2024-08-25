fun main(){
    var nome = ""
    var pags = 0
    var pagsLidas = 0
    println("Bem vindo!")
    println("Digite o nome do livro")
    //Entrada
    while (nome == ""){
        nome = readln()
        if (nome == "") println("Entrada inválida")
    }
    println("Digite o número de páginas")
    while (pags == 0){
         pags = readln().toIntOrNull() ?:0
        if (pags == 0) println("Entrada inválida")
    }
    println("Digite o número de páginas lidas")
    while (pagsLidas == 0 || pagsLidas > pags){
        pagsLidas = readln().toIntOrNull() ?:0
        if (pagsLidas == 0 || pagsLidas > pags) println("Entrada inválida")
    }
    //Saída
    println("Nome do livro: $nome")
    println("Progresso em porcentagem: ${100*pagsLidas/pags}%")
}
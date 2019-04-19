const listaIngredientes = [];
const idIngredientes = document.getElementById('ingredientes');
const  idTotalIngrediente = document.getElementById("totalIngrediente");

function obterIngredientes()
{
    fetch("http://localhost:8080/ingredientes")
    .then((body) => body.json())
    .then((data) => {
        criarListaIngredientes(data);
    })
    .catch((error) => console.error('Whoops! Erro:', error.message || error))
}

function criarListaIngredientes(ingredientes)
{
    const containerListaIngredientes = document.querySelector(".lista-ingredientes");
    if (containerListaIngredientes != null)
    {
        ingredientes.map(function (ingrediente)
        {
            containerListaIngredientes.insertAdjacentHTML('beforeend', `
                <div class="card vertical" style="padding: 15px;">
                    <div class="container-informacoes">
                    <div class="container-descricao" style="width: 100%">
                        <h4 style="margin-bottom:0px;">${ingrediente.nome}</h4>
                        <h5 style="margin-top:0px;">${  converterValorToMoeda(ingrediente.preco)}</h5>
                    </div>
                    <div class="container-botoes">
                            <button style="margin-right: 10px;" class="btn-floating btn-large waves-effect waves-light cian" onclick="addIngrediente(${ingrediente.id},'${ingrediente.nome}', ${ingrediente.preco}); return false;">
                            <i class="fas fa-plus"></i>
                            </button>
                            <button class="btn-floating btn-large waves-effect waves-light red" onclick="removeIngrediente(${ingrediente.id}); return false;">
                            <i class="fas fa-minus"></i>
                            </button>
                    </div>
                </div>
            `);
        });
    }
}

function addIngrediente(id, nomeIngrediente, preco)
{
    listaIngredientes.push({ "id": id, "nome": nomeIngrediente, "preco": preco });
    idIngredientes.innerText = ingredientesSeparadosVirgula(listaIngredientes);
    idTotalIngrediente.innerText = "Total "+ calcularValorPersonalizado(listaIngredientes);
}

function removeIngrediente(id)
{
    var achouIndex = -1;
    for (var index = listaIngredientes.length - 1; index >= 0; index--)
    {
        if (achouIndex == -1)
        {
            if (listaIngredientes[index].id === id) { achouIndex = index }
        }
    }

    if (achouIndex > -1) { listaIngredientes.splice(achouIndex, 1);  }

    idIngredientes.innerText = ingredientesSeparadosVirgula(listaIngredientes);
    idTotalIngrediente.innerText =  "Total "+calcularValorPersonalizado(listaIngredientes);
}

function adicionarPersonalizadoCarrinho()
{
    if(listaIngredientes.length > 0 )
    {
        var xPersonalizado = new Object();
        xPersonalizado.nomeLanche =  "X-Personalizado";
        xPersonalizado.ingredientes =  listaIngredientes.slice();
        xPersonalizado.valor = 0.0;
        carrinhoAuxiliar.push(xPersonalizado);
        
        
        localStorage.setItem("cart", JSON.stringify(carrinhoAuxiliar));
        document.querySelector(".container-carrinho > .badge").innerText = carrinhoAuxiliar.length;
        listaIngredientes.length = 0;
        idIngredientes.innerText = "";
        idTotalIngrediente.innerText = "Total R$ 0,00";
    }
}

function calcularValorPersonalizado(listaIngredientes)
{
    var valor = 0.0;
    listaIngredientes.map(function(ingrediente){
        valor += ingrediente.preco;
    });

    return converterValorToMoeda(valor);
}
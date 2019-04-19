document.querySelector(".container-carrinho > .badge").innerText = carrinhoAuxiliar.length;
const containerListaLanches = document.querySelector(".container-lanche");
var cardapiolanches ;

function obterCardapio()
{
    cardapiolanches = null;
    fetch("http://localhost:8080/lanches/cardapio")
    .then((body) => body.json())
    .then((data) => {
        cardapiolanches = data;
        criarListaLanches(data);
    })
    .catch((error) => console.error('Whoops! Erro:', error.message || error))
}




function criarListaLanches(lanches)
{
    if (containerListaLanches != null)
    {
        lanches.map(function (lanche)
        {
            containerListaLanches.insertAdjacentHTML('beforeend', `
                <div class="card vertical" style="padding: 15px;">
                    <div class="container-informacoes">
                        <img style="height: 50px; width: 50px;" src="./imagens/lanche.png" />
                        <div style="padding-left:5px;display:flex;flex-direction:column;width: 100%">
                            <h4 style="margin-bottom: 0px;">${lanche.nomeLanche}</h4>
                            <h5 style="margin-top: 0px;"> ${ converterValorToMoeda(lanche.valor)}</h6>
                        </div>
                        <div>
                            <button class="btn-floating btn-large waves-effect waves-light cian" onclick="addCartObj('${lanche.nomeLanche} '); return false;">
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
                    </div>
                    <div>
                        <div>
                            <span>Ingredientes:</span> ${ ingredientesSeparadosVirgula(lanche.ingredientes)}
                        </div>
                    </div>
                </div>
            `);
        });
    }
}

function addCartObj(nomeDoLanche)
{
    cardapiolanches.map(function (lanche)
    {
        if (lanche.nomeLanche.trim() === nomeDoLanche.trim())
        {
            carrinhoAuxiliar.push(lanche);
            localStorage.setItem("cart", JSON.stringify(carrinhoAuxiliar));
            document.querySelector(".container-carrinho > .badge").innerText = carrinhoAuxiliar.length;
        }
    });
}


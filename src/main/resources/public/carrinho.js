
const carrinhoAuxiliar = localStorage.getItem("cart") != null ? JSON.parse(localStorage.getItem("cart")) : [];
const badgeCarrinho = document.querySelector(".container-carrinho > .badge");

if (badgeCarrinho !== null) { badgeCarrinho.innerText = carrinhoAuxiliar.length; }


function obterCompras()
{
  fetch("http://localhost:8080/lanches/calcula-valor", {
    method: 'post',
    headers: {
      'Accept': 'application/json, text/plain, */*',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(carrinhoAuxiliar)
  })
    .then((body) => body.json())
    .then((data) => {
      localStorage.setItem("cart", JSON.stringify(data));
      criarListaIngredientes(data);
    })
    .catch((error) => console.error('Whoops! Erro:', error.message || error))
}

function criarListaIngredientes(compras)
{
  var valorTotal = 0.0;
  const idListarCompras = document.getElementById('listarCompras');
  idListarCompras.innerHTML = null;

  if (idListarCompras != null && compras != null)
  {
    compras.map(function (compra, index)
    {
      valorTotal += compra.valor;

      idListarCompras.insertAdjacentHTML('beforeend', `
          <div class="card vertical" style="padding: 15px;">
              <div class="container-informacoes">
                  <img style="height: 50px; width: 50px;" src="./imagens/lanche.png" />
                  <div style="padding-left:5px;display:flex;flex-direction:column;width: 100%">
                      <h4 style="margin-bottom: 0px;">${compra.nomeLanche}</h4>
                      <h5 style="margin-top: 0px;"> ${ converterValorToMoeda(compra.valor)}</h6>
                  </div>
                  <div>
                    <button class="btn-floating btn-large waves-effect waves-light red" onclick="removeLancheCarrinho('${index}'); return false;">
                      <i class="fas fa-minus"></i>
                    </button>
                  </div>
              </div>
              <div>
                  <div>
                      <span>Ingredientes:</span> ${ ingredientesSeparadosVirgula(compra.ingredientes)}
                  </div>
              </div>
          </div>
      `);
    });
  }

  document.getElementById("totalPedido").innerHTML = "Total " + converterValorToMoeda(valorTotal);

}

function removeLancheCarrinho(index)
{
  carrinhoAuxiliar.splice(index, 1);
  localStorage.setItem("cart", JSON.stringify(carrinhoAuxiliar));
  criarListaIngredientes(carrinhoAuxiliar);
}

function getPromocaoDia()
{
  const idPromocaoDia = document.getElementById('promocaoDia');
  fetch("http://localhost:8080/promocao")
    .then((body) => body.json())
    .then((data) => {
      idPromocaoDia.innerText = "Promoção do Dia : " + data;
    })
    .catch((error) => console.error('Whoops! Erro:', error.message || error))
}

function finalizarPedido()
{
  var pedido = { lanche: carrinhoAuxiliar }

  if(carrinhoAuxiliar != null && carrinhoAuxiliar.length > 0)
  {
    fetch("http://localhost:8080/pedidos", {
      method: 'post',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(pedido)
    })
    .then((body) => body.json())
    .then((data) => {
      compraRealizada();
    })
    .catch((error) => console.error('Whoops! Erro:', error.message || error))
  }
}

function compraRealizada()
{
  const modalCompraRealizada = document.getElementById("modalCompraRealizada");
  var instances = M.Modal.init(modalCompraRealizada, {dismissible:false});
  instances.open();
  
  carrinhoAuxiliar.splice(0, carrinhoAuxiliar.length)
  localStorage.setItem("cart", JSON.stringify(carrinhoAuxiliar));
}

function ingredientesSeparadosVirgula(ingredientes)
{
  const array = [];
  for (let index = 0; index < ingredientes.length; index++)
  {
    array.push(ingredientes[index].nome);
  }
  return array.join(", ");
}

function converterValorToMoeda(valor)
{
  return valor.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' });
}
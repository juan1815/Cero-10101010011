// Seleccionar el contenedor del gráfico
const chartContainer = document.getElementById('chart-container');

// Añadir la clase "hovered" al elemento de la lista seleccionado
const listItems = document.querySelectorAll(".navigation li");

function activateLink() {
    listItems.forEach((item) => {
        item.classList.remove("hovered");
    });
    this.classList.add("hovered");
}

listItems.forEach((item) => item.addEventListener("mouseover", activateLink));

// Alternar el menú
const toggle = document.querySelector(".toggle");
const navigation = document.querySelector(".navigation");
const main = document.querySelector(".main");

toggle.addEventListener("click", function() {
    navigation.classList.toggle("active");
    main.classList.toggle("active");
});

// Gráficos
function initChart(months, salesCount) {
    // Configuración de la gráfica utilizando Chart.js
    var ctx = document.getElementById('ordersChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar', // Tipo de gráfica (en este caso, de barras)
        data: {
            labels: months, // Nombres de los meses en el eje X
            datasets: [{
                label: 'Ventas por Mes', // Etiqueta para la leyenda
                data: salesCount, // Datos de las cantidades de ventas
                backgroundColor: 'rgba(54, 162, 235, 0.2)', // Color de fondo para las barras
                borderColor: 'rgba(54, 162, 235, 1)', // Color del borde para las barras
                borderWidth: 1 // Ancho del borde para las barras
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true // Comenzar el eje Y desde cero
                }
            }
        }
    });
}


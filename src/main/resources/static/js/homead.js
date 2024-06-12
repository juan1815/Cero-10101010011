var chartContainer = document.getElementById('chart-container');
//add hovered class to selected list item
let list = document.querySelectorAll(".navigation li")
function activeLink() {
    list.forEach((item) => {
        item.classList.remove("hovered");
});
this.classList.add("hovered")
} 

list.forEach((item) => item.addEventListener("mouseover", activeLink));


//menu toggle
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick =function () {
    navigation.classList.toggle("active");
    main.classList.toggle("active");
};

//________GRAFICAS_____________________
// Datos de ejemplo para las ventas mensuales
const ventasMensuales = [500, 700, 1000, 900, 1200, 1500, 1700, 1400, 1300, 1600, 1800, 2000];
const meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

// Configuración de la gráfica de ventas mensuales
const ctx1 = document.getElementById('ventasChart').getContext('2d');
const ventasChart = new Chart(ctx1, {
    type: 'bar',
    data: {
        labels: meses,
        datasets: [{
            label: 'Ventas Mensuales',
            data: ventasMensuales,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

// Datos de ejemplo para las ventas mensuales por producto
const ventasLlaveros = [50, 70, 100, 90, 120, 150, 170, 140, 130, 160, 180, 200];
const ventasPlacas = [30, 40, 60, 70, 90, 110, 130, 120, 110, 130, 140, 160];
const ventasPines = [20, 30, 40, 50, 60, 80, 100, 90, 100, 110, 120, 140];

// Configuración de la gráfica de ventas por producto
const ctx2 = document.getElementById('ventasProductoChart').getContext('2d');
const ventasProductoChart = new Chart(ctx2, {
    type: 'bar',
    data: {
        labels: meses,
        datasets: [
            {
                label: 'Llaveros',
                data: ventasLlaveros,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            },
            {
                label: 'Placas de Identificación',
                data: ventasPlacas,
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            },
            {
                label: 'Pines',
                data: ventasPines,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }
        ]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
// Función para ajustar el tamaño del gráfico cuando cambia el tamaño de la ventana
function resizeChart() {
    var containerWidth = chartContainer.offsetWidth;
    myChart.canvas.parentNode.style.width = containerWidth + 'px';
    myChart.canvas.parentNode.style.height = Math.round(containerWidth / 2) + 'px';
}

// Ajustar el tamaño del gráfico cuando se carga la página y cuando cambia el tamaño de la ventana
window.onload = resizeChart;
window.onresize = resizeChart;

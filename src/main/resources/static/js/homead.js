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
  // Ventas Mensuales
        const ventasMensuales = /*[[${ventasMensuales}]]*/ [];
        const labelsMensuales = ventasMensuales.map(venta => venta.mes);
        const dataMensuales = ventasMensuales.map(venta => venta.total);

        // Ventas por Producto
        const ventasPorProducto = /*[[${ventasPorProducto}]]*/ [];
        const labelsProducto = ventasPorProducto.map(venta => venta.producto);
        const dataProducto = ventasPorProducto.map(venta => venta.total);

        // Chart.js Configuración
        const ctxMensuales = document.getElementById('ventasChart').getContext('2d');
        new Chart(ctxMensuales, {
            type: 'bar',
            data: {
                labels: labelsMensuales,
                datasets: [{
                    label: 'Ventas Mensuales',
                    data: dataMensuales,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

        const ctxProducto = document.getElementById('ventasProductoChart').getContext('2d');
        new Chart(ctxProducto, {
            type: 'bar',
            data: {
                labels: labelsProducto,
                datasets: [{
                    label: 'Ventas por Producto',
                    data: dataProducto,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
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

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

document.addEventListener("DOMContentLoaded", function() {
    const chartContainer = document.getElementById('chart-container');

    // Obtener datos de ventas mensuales y por producto desde Thymeleaf
    const ventasMensuales = /*[[${ventasMensuales}]]*/ [];
    const ventasPorProducto = /*[[${ventasPorProducto}]]*/ [];

    // Verificar que los datos lleguen correctamente
    console.log('Ventas Mensuales:', ventasMensuales);
    console.log('Ventas por Producto:', ventasPorProducto);

    // Función para crear gráfica de Ventas Mensuales
    function renderVentasMensualesChart() {
        const labelsMensuales = ventasMensuales.map(venta => venta.mes);
        const dataMensuales = ventasMensuales.map(venta => venta.total);

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
    }

    // Función para crear gráfica de Ventas por Producto
    function renderVentasProductoChart() {
        const labelsProducto = ventasPorProducto.map(venta => venta.producto);
        const dataProducto = ventasPorProducto.map(venta => venta.total);

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
    }

    // Llamar a las funciones para renderizar las gráficas
    renderVentasMensualesChart();
    renderVentasProductoChart();

    // Función para ajustar el tamaño del gráfico cuando cambia el tamaño de la ventana
    function resizeCharts() {
        var containerWidth = chartContainer.offsetWidth;

        // Ventas Mensuales
        const ctxMensuales = document.getElementById('ventasChart').getContext('2d');
        ctxMensuales.canvas.parentNode.style.width = containerWidth + 'px';
        ctxMensuales.canvas.parentNode.style.height = Math.round(containerWidth / 2) + 'px';

        // Ventas por Producto
        const ctxProducto = document.getElementById('ventasProductoChart').getContext('2d');
        ctxProducto.canvas.parentNode.style.width = containerWidth + 'px';
        ctxProducto.canvas.parentNode.style.height = Math.round(containerWidth / 2) + 'px';
    }

    // Ajustar el tamaño del gráfico cuando se carga la página y cuando cambia el tamaño de la ventana
    window.onload = resizeCharts;
    window.onresize = resizeCharts;
});


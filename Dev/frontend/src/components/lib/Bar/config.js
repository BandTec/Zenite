export const iniciarData = dados => {
	return {
		labels: dados.labels,
		datasets: [{
            label: dados.label,
			data: dados.data,
			backgroundColor: '#0066AC',
            hoverBackgroundColor: '#0066ACcc',
		}]
	}
}

export const options = {
    scales: {
        yAxes: [{
            ticks: {
                beginAtZero: true,
                max: 60,
                padding: 5,
                fontColor: '#0285C0',
            },
            gridLines: {
                drawOnChartArea: false,
                drawBorder: true,
                drawTicks: false,
                color: 'rgba(0,102,172,1)',
                lineWidth: 3
            },
            scaleLabel: {
                display: true,
                labelString: 'Minutos', 
                fontColor: '#0285C0',
              }
        }],
        xAxes: [{
            ticks: {
                padding: 10,
                fontColor: '#0285C0',
            },
            gridLines: {
                drawOnChartArea: false,
                drawBorder: true,
                drawTicks: false,
                color: 'rgba(0,102,172,1)',
                lineWidth: 3
            },
        }]
    },
    legend: {
        display: false,
    },
    maintainAspectRatio: false 
}
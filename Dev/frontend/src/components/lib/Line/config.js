export const iniciarData = dados => {
	return {
		labels: dados.labels,
		datasets: [{
			data: dados.data,
			backgroundColor: [
			'#0285C0',
			],
			hoverBackgroundColor: [
			'#0284c0ee',
			],
			borderColor: '#0066ACdd',
			fill: false
		}]
	}
}

export const options = {
	scales: {
		
		yAxes: [{
			ticks: {
				beginAtZero: true,
                padding: 5,
				fontColor: '#0285C0',
			},
			gridLines: {
				drawOnChartArea: false,
                drawBorder: true,
                drawTicks: false,
                color: 'rgba(0,102,172,1)',
                lineWidth: 3
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
                lineWidth: 3			}
		}]
	},
	legend: {
        display: false,
    },
    maintainAspectRatio: false
}
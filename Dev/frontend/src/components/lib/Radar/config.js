export const iniciarData = dados => {
	return {
		labels: dados.labels,
		datasets: [{
			data: dados.data,
			backgroundColor: [
			'#0285C0',
			'#0066AC',
			],
			hoverBackgroundColor: [
			'#0284c0ee',
			'#0066ACdd',
            ],
            borderColor: '#0066ACdd',
            fill: false
		}]
	}
}
export const options = {
    scale: {
        ticks: {
            beginAtZero: true,
            max: 60,
            fontColor: '#0285C0',
        },
        pointLabels: {
            fontColor:'#0285C0'
        }
    },
	legend: {
        display: false,
    },
    maintainAspectRatio: false
}
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
			]
		}]
	}
}
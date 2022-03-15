const name = document.getElementById("title");
const price = document.getElementById("price");
const button = document.getElementById("submitBtn");

button.addEventListener('click', async (e) => {
    e.preventDefault();
    const body = JSON.stringify({name: name.value, price: price.value})
    const url = "/products/add"

    try {
        const response = await fetch(url, {
            method: 'POST', // или 'PUT'
            body,
            headers: {'Content-Type': 'application/json'}
        });
        document.location.href = response.url;
    } catch (error) {
        console.error('Ошибка:', error);
    }
})
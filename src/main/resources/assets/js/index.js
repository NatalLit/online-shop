const name = document.getElementById("title");
const price = document.getElementById("price");
const productId = document.getElementById("productId");
const addProductButton = document.getElementById("submitAddProductBtn");
const editProductButton = document.getElementById("submitEditProductBtn");


console.log("It works");
console.log(addProductButton);
if(addProductButton){
addProductButton.addEventListener('click', async (e) => {
    e.preventDefault();
    const body = JSON.stringify({name: name.value, price: price.value});
    const url = "/products/add";

    try {
        const response = await fetch(url, {
            method: 'POST',
            body,
            headers: {'Content-Type': 'application/json'}
        });
        document.location.href = response.url;
    } catch (error) {
        console.error('Ошибка:', error);
    }
});
}

if(editProductButton){
console.log("It works 2");
editProductButton.addEventListener('click', async (e) => {
    e.preventDefault();
    const body = JSON.stringify({id:productId.value, name: name.value, price: price.value});
    const url = "/products/edit";
    console.log(body);
    try {
        const response = await fetch(url, {
            method: 'POST',
            body,
            headers: {'Content-Type': 'application/json'}
        });
        document.location.href = response.url;
    } catch (error) {
        console.error('Ошибка:', error);
    }
});
}






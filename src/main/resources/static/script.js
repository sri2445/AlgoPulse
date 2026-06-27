function renderBars(array, indexA = -1, indexB = -1, type = "") {
    const container = document.getElementById("container");
    container.innerHTML = ""; 

    array.forEach((value, idx) => {
        const bar = document.createElement("div");
        bar.className = "bar";
        bar.style.height = `${value * 3}px`; 
        bar.innerText = value;

        if (idx === indexA || idx === indexB) {
            if (type === "COMPARE") {
                bar.style.backgroundColor = "#f1c40f"; 
            } else if (type === "SWAP") {
                bar.style.backgroundColor = "#e74c3c"; 
            }
        }
        container.appendChild(bar);
    });
}

async function startSorting() {
    const inputStr = document.getElementById("arrayInput").value;
    const algo = document.getElementById("algoSelect").value;
    const array = inputStr.split(",").map(num => parseInt(num.trim()));

    renderBars(array);

    const response = await fetch("http://localhost:8080/api/sort", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ array: array, algorithm: algo })
    });

    const steps = await response.json();

    for (let step of steps) {
        renderBars(step.currentArray, step.indexA, step.indexB, step.type);
        await new Promise(resolve => setTimeout(resolve, 400));
    }
}
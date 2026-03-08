/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


            function formatCurrencyVND(amount) {
                return amount.toLocaleString('vi-VN') + '₫';
            }

            function updateTotals() {
                let rows = document.querySelectorAll('.cart-main-table tbody tr[data-unit-price]');
                let total = 0;
                let anyChecked = false;

                rows.forEach(row => {
                    let checkbox = row.querySelector('.item-check');
                    let price = parseFloat(row.dataset.unitPrice);
                    let qty = parseInt(row.querySelector('.cart-qty-input').value);
                    let lineTotal = price * qty;

                    row.querySelector('.line-total').textContent =
                            formatCurrencyVND(lineTotal);

                    if (checkbox && checkbox.checked) {
                        total += lineTotal;
                        anyChecked = true;
                    }
                });

                document.getElementById('total-amount')
                        .textContent = formatCurrencyVND(total);

                document.getElementById('checkout-btn')
                        .disabled = !anyChecked;
            }

            document.addEventListener('click', e => {
                if (!e.target.classList.contains('cart-qty-btn'))
                    return;

                let input = e.target.parentElement.querySelector('.cart-qty-input');
                let value = parseInt(input.value);

                if (e.target.textContent === '+')
                    value++;
                else
                    value = Math.max(1, value - 1);

                input.value = value;
                updateTotals();
            });

            document.addEventListener('change', e => {

                if (e.target.id === 'check-all') {
                    document.querySelectorAll('.item-check')
                            .forEach(cb => cb.checked = e.target.checked);
                }

                if (e.target.classList.contains('item-check') || e.target.id === 'check-all') {
                    updateTotals();
                }
            });

            document.addEventListener('DOMContentLoaded', updateTotals);


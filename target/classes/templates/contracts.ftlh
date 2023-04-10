<#import "parts/common.ftl" as common>

<@common.page>
    <section>
        <div class="container">
            <table>
                <thead>
                <tr>
                    <th>Номер сделки</th>
                    <th>Номер квартиры</th>
                    <th>Тип оплаты</th>
                    <th>Статус контракта</th>
                </tr>
                </thead>
                <tbody>
                <#list orders as order>
                    <tr>
                        <td>${order.id()}</td>
                        <td>${order.flatId()}</td>
                        <td>${order.typeOfPay()}</td>
                        <td>${order.statusOfContract()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <#include "parts/pagination.ftl">
    </section>
</@common.page>
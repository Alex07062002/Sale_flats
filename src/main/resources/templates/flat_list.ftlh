<#import "parts/common.ftl" as common>

<@common.page>
    <section>
        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>Стоимость</th>
                        <th>Вход</th>
                        <th>Площадь</th>
                        <th>Тип цены</th>
                        <th>Тип получателя</th>
                        <th>Количество комнат</th>
                    </tr>
                </thead>
                <tbody>
                    <#list flats as flat>

                            <tr onclick="window.location.href='/book/${city}/${house}/${floor}/${flat.id()}'; return false">
                                <td>${flat.cost()}</td>
                                <td>${flat.entrance()}</td>
                                <td>${flat.square()}</td>
                                <td>${flat.typePrice()}</td>
                                <td>${flat.typeOwnerOfFlat()}</td>
                                <td>${flat.numberOfRooms()}</td>
                            </tr>

                    </#list>
                </tbody>
            </table>
        </div>
        <#include "parts/pagination.ftl">
    </section>
</@common.page>
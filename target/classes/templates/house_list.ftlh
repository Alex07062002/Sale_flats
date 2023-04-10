<#import "parts/common.ftl" as common>

<@common.page>
    <section>
        <div class="container">
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Адрес</th>
                </tr>
                </thead>
                <tbody>
                <#list houses as house>
                    <tr onclick="window.location.href='/book/${city}/${house.id()}'; return false">
                        <td>${house.name()}</td>
                        <td>${house.address()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <#include "parts/pagination.ftl">
    </section>
</@common.page>
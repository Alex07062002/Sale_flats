<#import "parts/common.ftl" as common>

<@common.page>
    <section>
        <div class="container">
            <table>
                <thead>
                <tr>
                    <th>Этаж</th>
                </tr>
                </thead>
                <tbody>
                <#list floors as floor>
                    <tr onclick="window.location.href='/book/${city}/${house}/${floor.id()}'; return false">
                        <td>${floor.floor()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <#include "parts/pagination.ftl">
    </section>
</@common.page>
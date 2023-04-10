<div class="container pagination">
    <div>
        <p>Номер страницы</p>
        <#list pagination.pages() as page>
            <form method="get">
                <input type="hidden" name="page" value="${page}">
                <input type="hidden" name="pageSize" value="${pagination.pageSize()}">
                <#if page==pagination.page()>
                    <input type="submit" value="${page + 1}" class="sign-in-button elevation-4 ">
                <#else>
                    <input type="submit" value="${page + 1}">
                </#if>
            </form>
        </#list>
    </div>

    <div>
        <p>Размер страницы</p>
        <#list pagination.pageSizes() as page>
            <form method="get">
                <input type="hidden" name="page" value="0">
                <input type="hidden" name="pageSize" value="${page}">
                <#if page==pagination.pageSize()>
                    <input type="submit" value="${page}" class="sign-in-button elevation-4 ">
                <#else>
                    <input type="submit" value="${page}">
                </#if>

            </form>
        </#list>
    </div>
</div>
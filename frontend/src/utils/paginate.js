export function paginate(items, pageNumber, pageSize){
    var begin = ((pageNumber-1) * pageSize);
    var end = begin + pageSize;
    return items.slice(begin, end);
}

export function getNumberOfPages(items, numberPerPage){
    return Math.ceil(items.length / numberPerPage);
}
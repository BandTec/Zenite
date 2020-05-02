  function isNull(element) {
    return element == null || element === false || (typeof element === typeof Object() && element.length === 0) || element == '';
}

module.exports = {
    isNull: isNull
};
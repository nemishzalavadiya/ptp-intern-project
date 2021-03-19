const imagePathProviderFromName = (name) => {
    let firstLowerCharacter = name.charAt(0).toLowerCase();
    let charCode = firstLowerCharacter.charCodeAt();
    let imagePath = '';
    //"m" ascii 109 [ img available from a-m ]
    if (charCode > 109) {
        imagePath = String.fromCharCode(charCode - 12);
    } else if (charCode < 97) {
        // 49 means remove "0" and add "a" to get in range [a-m]
        imagePath = String.fromCharCode(charCode + 49);
    } else {
        imagePath = firstLowerCharacter;
    }
    return `/company/${imagePath}.png`;
}

export default imagePathProviderFromName;
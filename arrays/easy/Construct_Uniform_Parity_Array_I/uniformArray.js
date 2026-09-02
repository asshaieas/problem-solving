/**
 * @param {number[]} nums1
 * @return {boolean}
 */
var uniformArray = function(nums1) {
    // Always true: count_odd !== 1 makes "all even" possible, 
    // count_odd >= 1 makes "all odd" possible — one always holds
    return true;
};
console.log(uniformArray[1, 2, 3]) 
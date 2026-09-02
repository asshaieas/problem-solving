from typing import Optional, List
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution:
    def nodesBetweenCriticalPoints(self, head: Optional[ListNode]) -> List[int]:
        prev = head
        current = head.next 
        index = 1 #because current starts at index 0 and current.next as index 1
        critical_points = []
        while current.next is not None:
            next_node = current.next
            if current.val > prev.val and current.val > next_node.val:
                #this mean the local maximum founded 
                critical_points.append(index)
            elif current.val < prev.val and current.val < next_node.val:
                # this means local minimum founded 
                critical_points.append(index)
            prev = current #move prev forward 
            current = current.next #also move current forward
            index += 1 
        if len(critical_points) < 2:
            return [-1, -1]
        maxDistance = critical_points[-1] - critical_points[0]
        minDistance = float('inf')
        for i in range(1, len(critical_points)):
            gap = critical_points[i] - critical_points[i - 1]
            if gap < minDistance:
                minDistance = gap
        return[minDistance, maxDistance]


# helper function 
def build_linked_list(values):
    head = ListNode(values[0])
    current = head
    for val in values[1:]:
        current.next = ListNode(val)
        current = current.next
    return head
test = Solution()
print(test.nodesBetweenCriticalPoints(build_linked_list([5,3,1,2,5,1,2])))

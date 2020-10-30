# -*- coding: utf-8 -*-
"""
Created: Tue Oct 18 23:04:35 2020
Last modified: Tue Oct 27 23:04:35 2020
@author: David ING, Zian LI, Kuo CHEN
"""
import json
from tkinter.filedialog import askopenfilename

#set it to True if we want to see the flow of the algorithm before finding a feasible solution
debug_flag = False
#These constants are used principally as flags to tell the parent to continue the search or jut give up
#We want three options in our algorithm: 1) Continue, 2) Stop (i.e. Abandon), or 3) found solution.
continue_search = 1
abandon_search = 2

def allocate(data, ans = None, resources_usage = None, intervention_index = 0):
    '''
         Intervention schedule allocation based upon backtracking
    '''
    
    #################################################
    # Helper functions
    #################################################

    def increment_resources_usage(reported_key_error):
        '''
            Updates resources_usage based upon current intervention
        '''
        for resource_name, v in intervention["workload"].items():
            # Using generator expression to check if the day we want to assign intervention, resources wont be available for that day
            #before assigning the intervention for this day check to see if there is resource to use for that day or not  
            # i.e we want to verify that this starting day has the necessary resources to run or not   
            missing_for_day = next((day_iter for day_iter in range(day, day+int(delta)) if not v.get(str(day_iter))), None) #The second argument (None) avoids the error by telling next to return None when there is no value.
            #print(missing_for_day)      
            if missing_for_day:
                return (resource_name, missing_for_day), reported_key_error
                
            for day_iter in range(day, day+int(delta)):
                # Adding resources for all days to all the kind of resources involve with this intervention
                s_day_temp = str(day_iter)
                try:
                    #type of involved resources are not in resources_usage 
                    if resource_name not in resources_usage:
                        # Initialize each of them with all zero values of length T
                        resources_usage[resource_name] = [0]*data["T"]
                    #sum all types of resources that an intervention need to use during its action      
                    resources_usage[resource_name][day_iter-1] += v[s_day_temp][str_day]

                except KeyError:
                    #report key error with the key resources
                    if not reported_key_error:
                        print('\tKey error with')
                        print(f'\t\tIntervention {intervention_name}, Resource {resource_name}')
                        print(f'\t\tDay {str_day}')
                        
                    return None, True
        else:
            return None, reported_key_error
                    
        
    def remove_last_resources():
        '''
            Remove last resources added when an intervention is not compatible
        '''
        for resource_name, v in intervention["workload"].items():
            #search through the day an intervention takes the action 
            for day_iter in range(day, day+int(delta)):
                s_day_temp = str(day_iter)
                try:
                    #type of involved resources are not in resources_usage 
                    if resource_name not in resources_usage:
                        #type of involved resources are not in resources_usage     
                        resources_usage[resource_name] = [0]*data["T"] 
                    #remove back all types of resources when an intervention cannot insert for that day      
                    resources_usage[resource_name][day_iter-1] -= v[s_day_temp][str_day]

                except KeyError:
                    if not reported_key_error:
                        print('\tremove_last_resources Key error with')
                        print(f'\t\tIntervention {intervention_name}, Resource {resource_name}')
                        print(f'\t\tDay {str_day}')
         
    def sufficient_resources():
        '''
            Check if resources are sufficient for current answer list
        '''
        resources = data["Resources"]
        sufficient = True
        for resource_name, value in resources_usage.items():
            for day_iter in range(day, day+int(delta)):
                if value[day_iter-1] > resources[resource_name]["max"][day_iter-1]:
                    if debug_flag:
                        print('\tinsufficient_resources', intervention_name, resource_name, day)
                        print('\tHave', resources[resource_name]["max"][day_iter-1], 'Need', value[day_iter-1])
                    sufficient = False
                    break
            if not sufficient:
                break
        return sufficient
        
    def exclusion():
        '''
            Check if current intervention should be excluded based upon the answer list so far
        '''
        # Check on Exclusion for current intervetion before assigning
        winter = data["Seasons"]['winter']
        summer = data["Seasons"]['summer']
        isa = data["Seasons"]['is']
        if str_day in winter:
            season = "winter"
        elif str_day in summer:
            season = "summer"
        elif str_day in isa:
            season = "is"
        else:
            season = ""
        #Access to exclusion in json file
        exclusions = data["Exclusions"]
        conflict = None
        for k, v_lst in exclusions.items():
            if season in v_lst and intervention_name in v_lst:
                #check through the current ans list
                for ans_response in ans:
                    if day == ans_response["Day"] and ans_response["intervention_name"] in v_lst:
                        conflict = ans_response
                        break
                if conflict:
                    break
                    
        if debug_flag and conflict: 
            print("\texclusion", intervention_name, str_day, conflict["Day"], conflict["intervention_name"])
        return conflict
            
    #################################################
    # Main function
    #################################################

    #if solution is not yet assign
    if ans is None:
        ans = []  #list ans
        resources_usage = {} #dict of usage resources when assigning current intervention
    
    if intervention_index == len(data["Interventions"]):
        # if handled all the interventions assignment then return solution
        return ans
        
    # Check next intervention
    # Uses property that entries in dictionaries in Python are in order of creation
    intervention_name, intervention = list(data["Interventions"].items())[intervention_index]
    #last_day of the project
    last_day = data["T"]
    
    if debug_flag: # for debugging
        print(intervention_name, intervention_index, 'T', last_day)
        
    reported_key_error = False
    #tracks how many days we couldn't execute the for loop due to missing resources
    missing_resource_days = 0
    #counts how many days we did execute the for loop.
    test_day_cnt = 0

    #search through each day to assign the intervention start from first day(i.e. T = 1)
    for day in range(1, last_day + 1):
        str_day = str(day)
        if debug_flag: # for debugging
            print('\tday', day, intervention["Delta"][day-1], intervention_name, intervention_index)
        
        # number of days an intervention will run
        delta = intervention["Delta"][day-1]
        #starting day must be smaller or at most equal to tmax and must finish at most within last day of the project
        if day <= int(intervention["tmax"]) and day + delta - 1 <= last_day:
            test_day_cnt += 1
    
            # for assigning an intervention, add all types of resources needed to use for this intervetion to be in action
            missing_resources, reported_key_error = increment_resources_usage(reported_key_error)
            #print(missing_resources,reported_key_error)
            if missing_resources:
                missing_resource, missing_for_day = missing_resources
                missing_resource_days += 1
                if debug_flag: # for debugging
                    print('\tmissing resources', intervention_name, day, missing_resource, missing_for_day)
                continue # one resource has no resources for a day

            #All types of resources and exclusion valided for the current intervention 
            if sufficient_resources() and not exclusion():
                # Intervention response
                response = {"intervention_name": intervention_name, "Day": day, "Delta": intervention["Delta"][day-1]}

                # ans + ans + [response] duplicates ans list with response appended at end
                if debug_flag: print('\tforward search day', day)
                #recursive to search for assigning another interventions
                forward_search = allocate(data, ans + [response], resources_usage, intervention_index + 1)

                if isinstance(forward_search, list):
                    # Found answer
                    return forward_search

                elif forward_search == continue_search:
                    #remove last resources added cause the intervention is not compatible with this day
                    remove_last_resources()
                else:
                    return forward_search
                
            else:
                # insufficient resources, or intervention is excluded to work with current list
                if debug_flag: print('insufficient or conflict')
                remove_last_resources()
    else:
        if missing_resource_days == test_day_cnt:
            # Can't run since missing resources
            return abandon_search #return to the the parent forward_search
        else:      # Could not find a day
            return continue_search #return to the parent forward_search



#################################################
    # Testing Part
#################################################

#Test file by insert the specific instance and generate the solution file txt in the same location as A_X.json (e.g A_08.json --> A_08.txt)
filename = askopenfilename()
with open(filename, 'r') as f:
    data = json.load(f)                             

answer = allocate(data) 

if isinstance(answer, list):
    f = open(str(filename).replace(".json","") + ".txt","w+")
    for response in answer:
            #print(response["intervention_name"], response["Day"])
            f.write(response["intervention_name"]+" "+ str(response["Day"])+"\n")
    f.close()    
        
elif answer == continue_search:
    print('\tNo Solution, search exhausted')
else:
    print('\tNo Solution, search abandoned due to missing resources')
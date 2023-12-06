from scipy.spatial import distance
import copy
import math
import operator

def is_warm(lab_b, a):
    '''
    파라미터 lab_b = [skin_b, hair_b, eye_b]
    a = 가중치 [skin, hair, eye]
    질의색상 lab_b값에서 warm의 lab_b, cool의 lab_b값 간의 거리를
    각각 계산하여 warm이 가까우면 1, 반대 경우 0 리턴
    '''
    # standard of skin, eyebrow, eye
    ##warm_b_std = [11.6518, 11.71445, 3.6484]
    warm_b_std = [19.47125, 15.93125, 9.8575]
    ##cool_b_std = [4.64255, 4.86635, 0.18735]
    cool_b_std = [18.585, 13.00625, 8.73]

    warm_dist = 0
    cool_dist = 0

    body_part = ['skin', 'eyebrow', 'eye']
    for i in range(3):
        warm_dist += abs(lab_b[i] - warm_b_std[i]) * a[i]
        #print(body_part[i],"의 warm 기준값과의 거리")
        #print(abs(lab_b[i] - warm_b_std[i]))
        cool_dist += abs(lab_b[i] - cool_b_std[i]) * a[i]
        #print(body_part[i],"의 cool 기준값과의 거리")
        #print(abs(lab_b[i] - cool_b_std[i]))
    if(warm_dist <= cool_dist):
        return 1 #warm
    else:
        return 0 #cool

def is_spr(hsv_s, a):
    '''
    파라미터 hsv_s = [skin_s, hair_s, eye_s]
    a = 가중치 [skin, hair, eye]
    질의색상 hsv_s값에서 spring의 hsv_s, fall의 hsv_s값 간의 거리를
    각각 계산하여 spring이 가까우면 1, 반대 경우 0 리턴
    '''
    #skin, hair, eye
    #spr_s_std = [18.59296, 30.30303, 25.80645]
    spr_s_std = [17.83296, 15.40303, 10.49645]
    #fal_s_std = [27.13987, 39.75155, 37.5]
    fal_s_std = [21.83987, 14.75155, 9.5]
    spr_dist = 0
    fal_dist = 0

    body_part = ['skin', 'eyebrow', 'eye']
    for i in range(3):
        spr_dist += abs(hsv_s[i] - spr_s_std[i]) * a[i]
        #print(body_part[i],"의 spring 기준값과의 거리")
        #print(abs(hsv_s[i] - spr_s_std[i]) * a[i])
        fal_dist += abs(hsv_s[i] - fal_s_std[i]) * a[i]
        #print(body_part[i],"의 fall 기준값과의 거리")
        #print(abs(hsv_s[i] - fal_s_std[i]) * a[i])

    if(spr_dist <= fal_dist):
        return 1 #spring
    else:
        return 0 #fall

def is_smr(hsv_s, a):
    '''
    파라미터 hsv_s = [skin_s, hair_s, eye_s]
    a = 가중치 [skin, hair, eye]
    질의색상 hsv_s값에서 summer의 hsv_s, winter의 hsv_s값 간의 거리를
    각각 계산하여 summer가 가까우면 1, 반대 경우 0 리턴
    '''
    #skin, eyebrow, eye
    smr_s_std = [20.5, 13.9195, 11.77064]
    wnt_s_std = [14.43913, 13.2276, 11.9326]
    a[1] = 0.5 # eyebrow 영향력 적기 때문에 가중치 줄임

    smr_dist = 0
    wnt_dist = 0

    body_part = ['skin', 'eyebrow', 'eye']
    for i in range(3):
        smr_dist += abs(hsv_s[i] - smr_s_std[i]) * a[i]
        #print(body_part[i],"의 summer 기준값과의 거리")
        #print(abs(hsv_s[i] - smr_s_std[i]) * a[i])
        wnt_dist += abs(hsv_s[i] - wnt_s_std[i]) * a[i]
        #print(body_part[i],"의 winter 기준값과의 거리")
        #print(abs(hsv_s[i] - wnt_s_std[i]) * a[i])

    if(smr_dist <= wnt_dist):
        return 1 #summer
    else:
        return 0 #winter







def is_spr_light(lab_b, a):#웜 라이트 브라이트 구분
    '''
    파라미터 lab_b = [skin_b, hair_b, eye_b]
    a = 가중치 [skin, hair, eye]
    질의색상 lab_b값에서 warm의 lab_b, cool의 lab_b값 간의 거리를
    각각 계산하여 warm이 가까우면 1, 반대 경우 0 리턴
    '''
    # standard of skin, eyebrow, eye
    ##warm_b_std = [11.6518, 11.71445, 3.6484]
    spr_light_std = [17.2018, 16.01045, 10.89484]
    ##cool_b_std = [4.64255, 4.86635, 0.18735]
    spr_blight_std = [18.64255, 14.6635, 9.98735]

    spr_light_dist = 0
    spr_blight_dist = 0

    body_part = ['skin', 'eyebrow', 'eye']
    for i in range(3):
        spr_light_dist += abs(lab_b[i] - spr_light_std[i]) * a[i]
        #print(body_part[i],"의 warm 기준값과의 거리")
        #print(abs(lab_b[i] - warm_b_std[i]))
        spr_blight_dist += abs(lab_b[i] - spr_blight_std[i]) * a[i]
        #print(body_part[i],"의 cool 기준값과의 거리")
        #print(abs(lab_b[i] - cool_b_std[i]))
    if(spr_light_dist <= spr_blight_dist):
        return 1 #warm
    else:
        return 0 #cool



def is_summer_light(lab_b, a):#웜 라이트 브라이트 구분
    '''
    파라미터 lab_b = [skin_b, hair_b, eye_b]
    a = 가중치 [skin, hair, eye]
    질의색상 lab_b값에서 warm의 lab_b, cool의 lab_b값 간의 거리를
    각각 계산하여 warm이 가까우면 1, 반대 경우 0 리턴
    '''
    # standard of skin, eyebrow, eye
    ##warm_b_std = [11.6518, 11.71445, 3.6484]
    summer_light_std = [18.5518, 17.791445, 13.605484]
    ##cool_b_std = [4.64255, 4.86635, 0.18735]
    summer_mute_std = [21.14255, 12.86635, 10.18735]

    summer_light_dist = 0
    summer_mute_dist = 0

    body_part = ['skin', 'eyebrow', 'eye']
    for i in range(3):
        summer_light_dist += abs(lab_b[i] - summer_light_std[i]) * a[i]
        #print(body_part[i],"의 warm 기준값과의 거리")
        #print(abs(lab_b[i] - warm_b_std[i]))
        summer_mute_dist += abs(lab_b[i] - summer_mute_std[i]) * a[i]
        #print(body_part[i],"의 cool 기준값과의 거리")
        #print(abs(lab_b[i] - cool_b_std[i]))
    if(summer_light_dist <= summer_mute_dist):
        return 1 #warm
    else:
        return 0 #cool



def is_fall_mute(lab_b, a):#웜 라이트 브라이트 구분
    '''
    파라미터 lab_b = [skin_b, hair_b, eye_b]
    a = 가중치 [skin, hair, eye]
    질의색상 lab_b값에서 warm의 lab_b, cool의 lab_b값 간의 거리를
    각각 계산하여 warm이 가까우면 1, 반대 경우 0 리턴
    '''
    # standard of skin, eyebrow, eye
    ##warm_b_std = [11.6518, 11.71445, 3.6484]
    fall_mute_std = [22.42518, 14.001445, 9.0084]
    ##cool_b_std = [4.64255, 4.86635, 0.18735]
    fall_dark_std = [21.67255, 14.948635, 9.29735]

    fall_mute_dist = 0
    fall_dark_dist = 0

    body_part = ['skin', 'eyebrow', 'eye']
    for i in range(3):
        fall_mute_dist += abs(lab_b[i] - fall_mute_std[i]) * a[i]
        #print(body_part[i],"의 warm 기준값과의 거리")
        #print(abs(lab_b[i] - warm_b_std[i]))
        fall_dark_dist += abs(lab_b[i] - fall_dark_std[i]) * a[i]
        #print(body_part[i],"의 cool 기준값과의 거리")
        #print(abs(lab_b[i] - cool_b_std[i]))
    if(fall_mute_dist <= fall_dark_dist):
        return 1 #warm
    else:
        return 0 #cool


def is_winter_blight(lab_b, a):#웜 라이트 브라이트 구분
    '''
    파라미터 lab_b = [skin_b, hair_b, eye_b]
    a = 가중치 [skin, hair, eye]
    질의색상 lab_b값에서 warm의 lab_b, cool의 lab_b값 간의 거리를
    각각 계산하여 warm이 가까우면 1, 반대 경우 0 리턴
    '''
    # standard of skin, eyebrow, eye
    ##warm_b_std = [11.6518, 11.71445, 3.6484]
    winter_blight_std = [16.37518, 12.75445, 10.840484]
    ##cool_b_std = [4.64255, 4.86635, 0.18735]
    winter_dark_std = [11.99255, 13.86635, 13.403735]

    winter_blight_dist = 0
    winter_dark_dist = 0

    body_part = ['skin', 'eyebrow', 'eye']
    for i in range(3):
        winter_blight_dist += abs(lab_b[i] - winter_blight_std[i]) * a[i]
        #print(body_part[i],"의 warm 기준값과의 거리")
        #print(abs(lab_b[i] - warm_b_std[i]))
        winter_dark_dist += abs(lab_b[i] - winter_dark_std[i]) * a[i]
        #print(body_part[i],"의 cool 기준값과의 거리")
        #print(abs(lab_b[i] - cool_b_std[i]))
    if(winter_blight_dist <= winter_dark_dist):
        return 1 #warm
    else:
        return 0 #cool

import cv2
import numpy as np
from personal_color_analysis import tone_analysis
from personal_color_analysis.detect_face import DetectFace
from personal_color_analysis.color_extract import DominantColors
from colormath.color_objects import LabColor, sRGBColor, HSVColor
from colormath.color_conversions import convert_color

def analysis(imgpath):
    #######################################
    #           Face detection            #
    #######################################
    df = DetectFace(imgpath)
    face = [df.left_cheek, df.right_cheek,
            df.left_eyebrow, df.right_eyebrow,
            df.left_eye, df.right_eye]

    #######################################
    #         Get Dominant Colors         #
    #######################################
    temp = []
    clusters = 4
    for f in face:
        dc = DominantColors(f, clusters)
        face_part_color, _ = dc.getHistogram()
        #dc.plotHistogram()
        temp.append(np.array(face_part_color[0]))
    cheek = np.mean([temp[0], temp[1]], axis=0)
    eyebrow = np.mean([temp[2], temp[3]], axis=0)
    eye = np.mean([temp[4], temp[5]], axis=0)

    Lab_b, hsv_s = [], []
    color = [cheek, eyebrow, eye]
    for i in range(3):
        rgb = sRGBColor(color[i][0], color[i][1], color[i][2], is_upscaled=True)
        lab = convert_color(rgb, LabColor, through_rgb_type=sRGBColor)
        hsv = convert_color(rgb, HSVColor, through_rgb_type=sRGBColor)
        Lab_b.append(float(format(lab.lab_b,".2f")))
        hsv_s.append(float(format(hsv.hsv_s,".2f"))*100)
    f = open("./../LAB_chek.txt", "a")
    f.write('{}\{}\t{}\t{}'.format(imgpath, Lab_b[0],Lab_b[1],Lab_b[2])+"\n")
    #print('{}\{}'.format(imgpath, Lab_b))
    #print('Lab_b[skin, eyebrow, eye]',Lab_b)
    #print('hsv_s[skin, eyebrow, eye]',hsv_s)
    #######################################
    #      Personal color Analysis        #
    #######################################
    Lab_weight = [30, 20, 5]
    hsv_weight = [10, 1, 1]
    if(tone_analysis.is_warm(Lab_b, Lab_weight)):
        if(tone_analysis.is_spr(hsv_s, hsv_weight)):
            #봄웜톤 일때
            if(tone_analysis.is_spr_light(hsv_s, hsv_weight)):
                tone = '봄웜라이트(spring)'
            else:
                tone = '봄웜브라이트(spring)'
        else:
            #가을 웜톤일 때
            if(tone_analysis.is_fall_mute(hsv_s, hsv_weight)):
                tone = '가을웜뮤트(fall)'
            else:
                tone = '가을웜다크(fall)'
    else:
        if(tone_analysis.is_smr(hsv_s, hsv_weight)):
            if(tone_analysis.is_summer_light(hsv_s, hsv_weight)):
                tone = '여름쿨라이트(summer)'
            else:
                tone = '여름쿨뮤트(summer)'
        else:
            if(tone_analysis.is_winter_blight(hsv_s, hsv_weight)):
            #겨울의 경우
                tone = '겨울쿨브라이트(winter)'
            else:
                tone = '겨울쿨다크(winter)'

                  
    # Print Result
    #print('{}의 퍼스널 컬러는 {}입니다.'.format(imgpath, tone))
    if '봄웜라이트' in tone:
        print("0")
    if '봄웜브라이트' in tone:
        print("1")
    if '여름쿨라이트' in tone:
        print("2")
    if '여름쿨뮤트' in tone:
        print("3")
    if '가을웜뮤트' in tone:
        print("4")
    if '가을웜다크' in tone:
        print("5")
    if '겨울쿨브라이트' in tone:
        print("6")
    if '겨울쿨다크' in tone:
        print("7")
    print(tone)
    #print(tone)
    f=open("./../out.txt","a")
    f.write('{}의 퍼스널 컬러는 {}입니다.\n'.format(imgpath, tone))
    f.close()
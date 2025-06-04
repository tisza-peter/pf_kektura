import CircleStyle from 'ol/style/Circle';
import Fill from 'ol/style/Fill';
import Stroke from 'ol/style/Stroke';
import Style from 'ol/style/Style';
import TextStyle from 'ol/style/Text';

/**
 * Létrehoz egy OpenLayers stílust egy JSON definícióból
 * @param {string} jsonString A JSON string definíció
 * @returns {ol.style.Style}
 */
export function styleFromJson(jsonString: string) {
  const json = JSON.parse(jsonString);

  const stroke = json.stroke
    ? new Stroke({
        color: json.stroke.color,
        width: json.stroke.width,
        lineDash: json.stroke.lineDash,
        lineCap: json.stroke.lineCap,
        lineJoin: json.stroke.lineJoin
      })
    : undefined;

  const fill = json.fill
    ? new Fill({
        color: json.fill.color
      })
    : undefined;

  let image;
  if (json.image && json.image.type === 'circle') {
    image = new CircleStyle({
      radius: json.image.radius || 5,
      fill: json.image.fill ? new Fill({ color: json.image.fill.color }) : undefined,
      stroke: json.image.stroke ? new Stroke({ color: json.image.stroke.color, width: json.image.stroke.width }) : undefined
    });
  }

  const text = json.text
    ? new TextStyle({
        text: json.text.text,
        font: json.text.font,
        fill: json.text.fill ? new Fill({ color: json.text.fill.color }) : undefined,
        stroke: json.text.stroke ? new Stroke({ color: json.text.stroke.color, width: json.text.stroke.width }) : undefined,
        offsetX: json.text.offsetX,
        offsetY: json.text.offsetY,
        textAlign: json.text.textAlign
      })
    : undefined;

  return new Style({
    stroke,
    fill,
    image,
    text,
    zIndex: json.zIndex
  });
}

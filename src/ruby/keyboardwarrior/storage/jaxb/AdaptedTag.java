package ruby.keyboardwarrior.storage.jaxb;

import ruby.keyboardwarrior.common.Utils;
import ruby.keyboardwarrior.data.exception.IllegalValueException;
import ruby.keyboardwarrior.data.tag.Tag;

import javax.xml.bind.annotation.XmlValue;

//@@author A0144665Y
/**
 * JAXB-friendly adapted tag data holder class.
 */
public class AdaptedTag {

    @XmlValue
    public String tagName;

    /**
     * No-argument constructor for JAXB use.
     */
    public AdaptedTag() {}

    /**
     * Converts a given Tag into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AdaptedTag
     */
    public AdaptedTag(Tag source) {
        tagName = source.tagName;
    }

    /**
     * Check if any of the required filed is missing.
     * 
     * @return true if missing
     */
    public boolean isAnyRequiredFieldMissing() {
        return Utils.isAnyNull(tagName);
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the Tag object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Tag toModelType() throws IllegalValueException {
        return new Tag(tagName);
    }
}